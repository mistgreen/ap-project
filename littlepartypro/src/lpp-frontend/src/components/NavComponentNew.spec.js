import {shallowMount} from '@vue/test-utils'
import NavComponent from '@/components/NavComponent.vue'

// const makeFetchResp = (value) => ({ json: async () => value }) // mock fetch().json()
// const mockFetch = jest.fn().mockReturnValue(makeFetchResp(true))
global.fetch = jest.fn().mockImplementation(() => {
  return new Promise((resolve) => resolve({
        json: () => {
          return {data: 'hello'}
        },
      }),
  )
})

function mountComponent() {
  return shallowMount(NavComponent)
}

describe('Component: Nav', () => {
  let pageObject
  let fetchSpy

  beforeEach(() => {
    fetchSpy = jest.spyOn(global, 'fetch');
    pageObject = mountComponent()
  })

  describe('When performAction is called', () => {
    beforeEach(() => {
      pageObject.vm.performAction('login', 'Foo', 'Bar')
    })

    it('Should', () => {
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
        await pageObject.vm.performAction('login', 'Foo', 'Bar');
      })

      it('should set loginSuccessful to true', () => {
        expect(pageObject.vm.loginSuccessful).toBe(true);
        expect(pageObject.find('[data-qa-nav-component-logout-button]').exists()).toBe(true);
      })

      it('should display logged in message', () => {
        expect(pageObject.find('[data-qa-nav-component-logged-in-message]').exists()).toBe(true);
        expect(pageObject.find('[data-qa-nav-component-logged-in-message]').text()).toBe("Welcome, Foo!");
      })
    })
  })
})