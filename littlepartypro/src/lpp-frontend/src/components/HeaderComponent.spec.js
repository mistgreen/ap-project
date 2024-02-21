import {shallowMount} from '@vue/test-utils'
import HeaderComponent from '@/components/HeaderComponent.vue'

const mountComponent = () => {
  return shallowMount(HeaderComponent, {

  });
}

describe('Header Component', () => {
  let wrapper;

  describe('When component is mounted', () => {
    beforeEach(() => {
      wrapper = mountComponent();
    });

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-header-component]').exists()).toBe(true)
    });

    test('Should render the header messages', () => {
      expect(wrapper.find('[data-qa-header-component-h1]').text()).
      toBe('Plan your perfect party!')
      expect(wrapper.find('[data-qa-header-component-h2]').text()).
      toBe('From birthdays to baby showers, christenings to engagements, whatever the occasion - we\'ve got you covered.')
    });


  })
})