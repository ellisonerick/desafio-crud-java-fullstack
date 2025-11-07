<template>
  <div
    v-if="show"
    :class="['alert', alertClass, 'fade', 'show', 'position-fixed', 'top-0', 'end-0', 'm-3']"
    role="alert"
    style="z-index: 9999; min-width: 280px;"
  >
    {{ message }}
  </div>
</template>

<script>
export default {
  props: {
    type: { type: String, default: 'success' },
    message: { type: String, required: true }
  },
  data() {
    return {
      show: true
    }
  },
  computed: {
    alertClass() {
      return this.type === 'success'
        ? 'alert-success'
        : this.type === 'error'
        ? 'alert-danger'
        : 'alert-info'
    }
  },
  mounted() {
    setTimeout(() => {
      this.show = false
    }, 3000)
  }
}
</script>
