import {shallowMount} from '@vue/test-utils'
import LoginComponent from '@/components/LoginComponent.vue'

const mountComponent = (method, loginResponseStatus) => {
  return shallowMount(LoginComponent, {
    props: {
      modalActive: true,
      method: method,
      loginResponseStatus: loginResponseStatus,
      loginResponseMessage: String,
    },
  })
}

describe('Header Component', () => {
  let wrapper

  describe('When component is mounted', () => {
    beforeEach(() => {
      wrapper = mountComponent()
    })

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-login-component]').exists()).toBe(true)
    })

    describe('When login component is mounted', () => {
      beforeEach(() => {
        wrapper = mountComponent('login')
      })

      test('Should show message - login', () => {
        expect(wrapper.find('[data-qa-login-component-login-message]').text()).toBe('Login')
      });
    })

    describe('When register component is mounted', () => {
      beforeEach(() => {
        wrapper = mountComponent('register')
      })

      test('Should show message - register', () => {
        expect(wrapper.find('[data-qa-login-component-login-message]').text()).toBe('Register')
      });
    })

    describe('When close modal button is clicked', () => {
      test('Should emit close-modal event', () => {
        wrapper.find('[data-qa-login-component-button]').trigger('click');
        expect(wrapper.emitted()).toHaveProperty('close-modal')
        expect(wrapper.emitted('close-modal')).toBeTruthy()
        expect(wrapper.emitted('close-modal')).toHaveLength(1)
      });
    })

    describe('When submit button is clicked', () => {
      test('Should emit perform-action event', () => {
        wrapper.find('[data-qa-login-component-submit-button]').trigger('click');
        expect(wrapper.emitted()).toHaveProperty('perform-action')
        expect(wrapper.emitted('perform-action')).toBeTruthy()
        expect(wrapper.emitted('perform-action')).toHaveLength(1)
      });
    })

    describe('When response status is 200 and method is login', () => {
      beforeEach(() => {
        wrapper = mountComponent('login', 200)
      })
      test('Should return success message', () => {
        expect(wrapper.find('[data-qa-login-component-success-response-message]').text()).toBe('Login successful!')
      });
    })

    describe('When response status is 401 and method is login', () => {
      beforeEach(() => {
        wrapper = mountComponent('login', 401)
      })
      test('Should return fail message', () => {
        expect(wrapper.find('[data-qa-login-component-failure-response-message]').text()).toBe('Login failed')
      });
    })

    describe('When response status is 500 and method is login', () => {
      beforeEach(() => {
        wrapper = mountComponent('login', 500)
      })
      test('Should return fail message', () => {
        expect(wrapper.find('[data-qa-login-component-failure-response-message]').text()).toBe('Login failed')
      });
    })

  })
})