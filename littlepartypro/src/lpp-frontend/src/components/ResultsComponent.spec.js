import {shallowMount} from '@vue/test-utils'
import ResultsComponent from '@/components/ResultsComponent.vue'

const mountComponent = () => {
  return shallowMount(ResultsComponent,
  )
}
describe('Results Component', () => {
  describe('When component is mounted', () => {
  global.fetch = jest.fn().mockImplementation(() => {
    return new Promise((resolve) => resolve({
          json: () => {
            return {

            }
          },
        }),
    )
  })
  let wrapper
  let fetchSpy


    beforeEach(() => {
      fetchSpy = jest.spyOn(global, 'fetch')
      wrapper = mountComponent()
    })

    afterEach(() => {
      // Reset any mock implementations
      jest.clearAllMocks();
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-results-component]').exists()).toBe(true)
      expect(fetchSpy).toHaveBeenCalled()
    })

    describe('Search Component', () => {
      test('Search Component should be visible', () => {
        expect(wrapper.find('[data-qa-results-component-search-component]').
        exists()).toBe(true)
      })

      describe('When performSearch is called', () => {
        beforeEach(() => {
          wrapper.vm.performSearch('test query')
        })

        it('Should call search function', () => {
          expect(fetchSpy).toHaveBeenCalled()
        })

        describe('When performSearch is called with no results returned',
            () => {

              it('should render no results message', () => {
                console.log('here is the html' + wrapper.html())
                expect(wrapper.find(
                    '[data-qa-results-component-vendor-no-results-message]').
                exists()).toBe(true)
                expect(wrapper.find(
                    '[data-qa-results-component-vendor-no-results-message]').
                text()).
                toBe(
                    'Sorry, we couldn\'t find anything for test query.Hint: You can search by town (location) or by the company name.')
              })

            })
      })
    })

    describe('Category Component', () => {
      test('Category Component should be visible', () => {
        expect(
            wrapper.find('[data-qa-results-component-category-component]').
            exists()).
        toBe(true)
      })

      describe('When getType is called', () => {
        beforeEach(() => {
          wrapper.vm.getVendorType('test')
        })

        it('Should call getType function', () => {
          expect(fetchSpy).toHaveBeenCalled()
        })
      })
    })
  })
})


