import {shallowMount} from '@vue/test-utils'
import FooterComponent from '@/components/FooterComponent.vue'

const mountComponent = () => {
  return shallowMount(FooterComponent, {

  });
}

describe('Footer Component', () => {
  let wrapper;

  describe('When component is mounted', () => {
    beforeEach(() => {
      wrapper = mountComponent();
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-footer-component]').exists()).toBe(true)
    });

    test('Should add given category name', () => {
      expect(wrapper.find('[data-qa-footer-component]').text()).
      toBe('© 2024 Little Party Pro')
    });
  })
})