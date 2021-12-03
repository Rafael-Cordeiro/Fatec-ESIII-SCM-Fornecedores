
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      { path: 'fornecedores', component: () => import('pages/Fornecedores.vue') },
      { path: 'servicos', component: () => import('pages/Servicos.vue') },
      { path: 'auditoria', component: () => import('pages/Auditoria.vue') }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
