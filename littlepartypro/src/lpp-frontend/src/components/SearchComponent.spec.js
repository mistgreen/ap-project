// Import the necessary modules
import { mount } from '@vue/test-utils';
import SearchComponent from './SearchComponent.vue';

describe('SearchComponent', () => {
  it('renders the component', () => {
    const wrapper = mount(SearchComponent);
    expect(wrapper.exists()).toBe(true);
  });

  it('emits a perform-search event when the button is clicked', async () => {
    const wrapper = mount(SearchComponent);
    const input = wrapper.find('input');
    await input.setValue('test');
    const button = wrapper.find('button');
    await button.trigger('click');

    expect(wrapper.emitted('perform-search')).toBeTruthy();
    expect(wrapper.emitted('perform-search')[0]).toEqual(['test']);
  });

});
