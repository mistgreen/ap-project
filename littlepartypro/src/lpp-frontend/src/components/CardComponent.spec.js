import { shallowMount } from '@vue/test-utils';
import CardComponent from '@/components/CardComponent.vue'
import ButtonComponent from '@/components/ButtonComponent.vue'

const mountComponent = () => {
  return shallowMount(CardComponent, {
    props: {
      modalActive: true,
      vendor: {
        title: 'Test Vendor',
        shortDesc: 'Test short description',
        url: 'http://example.com'
      }
    }
  });
}

describe('Card Component', () => {
  let wrapper;

  describe('When component is mounted', () => {
    beforeEach(() => {
      wrapper = mountComponent();
      jest.spyOn(wrapper.vm, 'toggleModal');
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-card-component]').exists()).toBe(true)
    });

    test('Should add given title', () => {
      expect(wrapper.find('[data-qa-card-component-title]').text()).toBe('TEST VENDOR')
    });

    test('Should add given short description', () => {
      expect(wrapper.find('[data-qa-card-component-short-desc]').text()).toBe('Test short description.')
    });

    describe('When View button is clicked', () => {
      test('Should call toggleModal function', () => {
        wrapper.find('[data-qa-card-component-button]').trigger('click');
        expect(wrapper.vm.toggleModal).toHaveBeenCalled();
      });
    });
  })
})