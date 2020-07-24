import request from '@/utils/request'

// 查询文件/附件管理列表
export function listFile(query) {
  return request({
    url: '/oss/file/list',
    method: 'get',
    params: query
  })
}

// 查询文件/附件管理详细
export function getFile(ossId) {
  return request({
    url: '/oss/file/' + ossId,
    method: 'get'
  })
}

// 新增文件/附件管理
export function addFile(data) {
  return request({
    url: '/oss/file',
    method: 'post',
    data: data
  })
}

// 修改文件/附件管理
export function updateFile(data) {
  return request({
    url: '/oss/file',
    method: 'put',
    data: data
  })
}

// 删除文件/附件管理
export function delFile(ossId) {
  return request({
    url: '/oss/file/' + ossId,
    method: 'delete'
  })
}
