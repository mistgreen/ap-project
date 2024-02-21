
import CategoryComponent from '@/components/CategoryComponent.vue'
import {shallowMount} from '@vue/test-utils'

const mountComponent = () => {
  const categories = ["VENUE", "DECORATOR", "CATERER", "MISC"]
  return shallowMount(CategoryComponent, {

    props: {
      }
  });
}

describe('Card Component', () => {
  let wrapper;

  describe('When component is mounted', () => {
    beforeEach(() => {
      wrapper = mountComponent();
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-category-component]').exists()).toBe(true)
    });

    test('Should add given category name', () => {
      expect(wrapper.find('[data-qa-category-component-category]').text()).
      toBe('VENUE')
    });

    describe('When category image is clicked', () => {
      test('Should emit category-filter event', () => {
        wrapper.find('[data-qa-category-component-image]').trigger('click');
        expect(wrapper.emitted()).toHaveProperty('category-filter')
        expect(wrapper.emitted('category-filter')).toBeTruthy()
        expect(wrapper.emitted('category-filter')).toHaveLength(1)
      });
    })
  })
})