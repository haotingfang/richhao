import request from '@/utils/request'

// 查询会员卡列表
export function listCard(query) {
  return request({
    url: '',
    method: 'get',
    params: query
  })
}

// 查询会员卡详细
export function getCard(cardId) {
  return request({
    url: '/' + cardId,
    method: 'get'
  })
}

// 新增会员卡种类
export function addCard(data) {
  return request({
    url: '/',
    method: 'post',
    data: data
  })
}

// 修改会员卡种类
export function updateCard(data) {
  return request({
    url: '/',
    method: 'post',
    data: data
  })
}



// 会员卡状态修改
export function changeCardStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/',
    method: 'put',
    data: data
  })
}

// 删除会员卡种类
export function delCard(cardId) {
  return request({
    url: '/' + cardId,
    method: 'delete'
  })
}


