import { shallowMount, mount } from '@vue/test-utils';
import ResultsComponent from '@/components/ResultsComponent.vue'

const mountComponent = () => {
  return shallowMount(ResultsComponent,
  );
}

global.fetch = jest.fn().mockImplementation(() => {
  return new Promise((resolve) => resolve({
        json: () => {
          return {data: 'hello'}
        },
      }),
  )
})

describe('Results Component', () => {
  let wrapper;
  let fetchSpy;

  describe('When component is mounted', () => {
    beforeEach(() => {
      fetchSpy = jest.spyOn(global, 'fetch');
      wrapper = mountComponent();
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-results-component]').exists()).toBe(true)
    });

    describe('Search Component', () => {
      test('Search Component should be visible', () => {
        expect(wrapper.find('[data-qa-results-component-search-component]').exists()).
        toBe(true);
      });


    })

    describe('Category Component', () => {
      test('Category Component should be visible', () => {
        expect(wrapper.find('[data-qa-results-component-category-component]').exists()).
        toBe(true);
      });


    })



  })
})
