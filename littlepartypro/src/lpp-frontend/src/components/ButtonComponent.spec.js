import { shallowMount } from '@vue/test-utils';
import ButtonComponent from '@/components/ButtonComponent.vue'

const mountComponent = () => {
  return shallowMount(ButtonComponent, {
    props: {
      text: "Test Button"
    }
  });
}

describe('Button Component', () => {

  let wrapper;

  describe("When component is mounted", () => {
    beforeEach(() => {
      wrapper = mountComponent()
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-button-component]').exists()).toBe(true)
    });

    test('Should have given text', () => {
      expect(wrapper.find('[data-qa-button-component]').text()).toBe('Test Button')
    });

  });

});