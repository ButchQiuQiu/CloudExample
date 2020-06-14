
/**
 * 从cookie中取出token
 */
function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i].trim();
    if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
  }
  return "";
}

/**
 * 把一个属性放入cookie中
 */
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires=" + d.toGMTString();
  document.cookie = cname + "=" + cvalue + "; " + expires;
}

/**
 * 删除指定cookie
 * @param {cookie名} cname  
 */
function delCookie(cname) {
  var date = new Date();
  date.setTime(date.getTime() - 10000);
  document.cookie = name + "=v; expires=" + date.toGMTString();
}