import request from '@/utils/request'

// 查询会员卡列表
export function listCard(query) {
  return request({
    url: '/goods/card/list',
    method: 'get',
    params: query
  })
}

// 查询会员卡详细
export function getCard(cardId) {
  return request({
    url: '/goods/card/' + cardId,
    method: 'get'
  })
}

// 新增会员卡种类
export function addCard(data) {
  return request({
    url: '/goods/card',
    method: 'post',
    data: data
  })
}

// 修改会员卡种类
export function updateCard(data) {
  return request({
    url: '/goods/card',
    method: 'put',
    data: data
  })
}

// 删除会员卡种类
export function delCard(cardIds) {
  return request({
    url: '/goods/card/' + cardIds,
    method: 'delete'
  })
}


