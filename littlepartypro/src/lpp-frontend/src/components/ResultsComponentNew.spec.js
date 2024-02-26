import { shallowMount } from '@vue/test-utils';
import ResultsComponent from '@/components/ResultsComponent.vue';

describe('Results Component - Search results with results', () => {
// Mock the fetch call
  global.fetch = jest.fn().mockImplementation(() => {
    return Promise.resolve({
      ok: true,
      status: 200,
      json: () => Promise.resolve(
          [
            {
              id: 1,
              title: 'Example Title',
              address: 'Example Address',
              postcode: 'Example Postcode',
              shortDesc: 'Example short description',
              longDesc: 'Example long description',
              tierId: 1,
              vendorTypeId: 1,
            }]),
    })
  })

  let wrapper

  beforeEach(() => {
    wrapper = shallowMount(ResultsComponent)
  })

  test('should fetch data and update vendors state', async () => {
    // Trigger the method that fetches data
    await wrapper.vm.performSearch('test query')
    console.log(wrapper.html())

    // Assert that the vendors state is updated correctly
    expect(wrapper.vm.vendors).toEqual([
      {
        id: 1,
        title: 'Example Title',
        address: 'Example Address',
        postcode: 'Example Postcode',
        shortDesc: 'Example short description',
        longDesc: 'Example long description',
        tierId: 1,
        vendorTypeId: 1,
      },
    ])
    expect(
        wrapper.find('[data-qa-results-component-vendor-results]').
        exists()).
    toBe(true)
    expect(wrapper.find('[data-qa-results-component-vendor-results-message]').
    text()).
    toBe('Showing 1 results.')
    expect(wrapper.find('[data-qa-results-component-vendor-results-card]').
    exists()).
    toBe(true)
  })

})
