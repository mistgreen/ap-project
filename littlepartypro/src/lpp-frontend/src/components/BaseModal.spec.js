import { shallowMount } from '@vue/test-utils';
import BaseModal from './BaseModal.vue'

const mountComponent = () => {
  return shallowMount(BaseModal, {
    props: {
      modalActive: true,
      vendor: {
        title: 'Test Vendor',
        longDesc: 'Test description',
        url: 'http://example.com'
      }
    }
  });
}

describe('BaseModal', () => {
  let wrapper

  describe('When component is mounted', () => {
    beforeEach(() => {
      wrapper = mountComponent()
    })

    test('Should mount given component', () => {
      expect(wrapper.find('[data-qa-base-modal]').exists()).toBe(true)
    })

    test('Should add given title', () => {
      expect(wrapper.find('[data-qa-base-modal-title]').text()).toBe('Test Vendor')
    })

    describe('When close modal button is clicked', () => {
      test('Should emit close-modal event', () => {
        wrapper.find('[data-qa-base-modal-button]').trigger('click');
        expect(wrapper.emitted()).toHaveProperty('close-modal')
        expect(wrapper.emitted('close-modal')).toBeTruthy()
        expect(wrapper.emitted('close-modal')).toHaveLength(1)
      })
    })
  })

});
