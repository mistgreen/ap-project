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

global.fetch = jest.fn().mockImplementation(() => {
  return new Promise((resolve) => resolve({
        json: () => {
          return {data: 'hello'}
        },
      }),
  )
})

describe('Nav Component', () => {
  let wrapper;
  let fetchSpy;

  describe('When component is mounted', () => {
    beforeEach(() => {
      fetchSpy = jest.spyOn(global, 'fetch');
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

    describe('When performAction is called', () => {
      beforeEach(() => {
        wrapper.vm.performAction('login', 'Foo', 'Bar')
      })

      it('Should call perform action function', () => {
        expect(fetchSpy).toHaveBeenCalled()
      })

      describe('When performAction is called', () => {
        beforeEach(async () => {
          fetchSpy.mockResolvedValueOnce({ok: true,
            status: 200,
            statusText: 'OK',
            redirected: false,
            type: 'basic',
            url: 'http://localhost:3000/api/auth/login'}); // Mocking a successful response
          await wrapper.vm.performAction('login', 'Foo', 'Bar');
        })

        it('should set loginSuccessful to true', () => {
          expect(wrapper.vm.loginSuccessful).toBe(true);
          expect(wrapper.find('[data-qa-nav-component-logout-button]').exists()).toBe(true);
        })

        it('should display logged in message', () => {
          expect(wrapper.find('[data-qa-nav-component-logged-in-message]').exists()).toBe(true);
          expect(wrapper.find('[data-qa-nav-component-logged-in-message]').text()).toBe("Welcome, Foo!");
        })
      })
    })


  })
})
