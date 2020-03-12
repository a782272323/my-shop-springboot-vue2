
/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * 这里不能写死，是传登录界面的用户名给后台的
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  // const valid_map = ['admin', 'editor']
  // const valid_map = ['admin', 'editor']
  return str.indexOf(str.trim()) >= 0
}
