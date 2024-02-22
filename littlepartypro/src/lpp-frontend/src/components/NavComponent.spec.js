import { shallowMount, mount } from '@vue/test-utils';
import NavComponent from '@/components/NavComponent.vue'

const mountComponent = () => {
  return shallowMount(NavComponent, {
    props: {
      modalMethod: String,
      loginResponseStatus: Number,
      loggedInUsername: String,
      loginResponseMessage: String
      }
    });
}

describe('Nav Component', () => {
  let wrapper;

  describe('When component is mounted', () => {
    beforeEach(() => {
      wrapper = mountComponent();
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-nav-component]').exists()).toBe(true)
    });

    test('Should add given title', () => {
      expect(wrapper.find('[data-qa-nav-component-title]').text()).
      toBe('Little Party Pro')
    });

    test('Register button should be visible', () => {
      expect(wrapper.find('[data-qa-nav-component-register-button]').exists()).
      toBe(true);
    });

    test('Login button should be visible', () => {
      expect(wrapper.find('[data-qa-nav-component-login-button]').exists()).
      toBe(true);
    });


  })
})
