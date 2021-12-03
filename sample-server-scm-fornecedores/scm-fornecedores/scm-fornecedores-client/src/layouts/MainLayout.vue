<template>
  <q-layout view="hHh lpR fff">

    <q-header elevated class="bg-indigo-12 text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <strong>Supply Chain Management</strong> Gerenciamento de Fornecedores
        </q-toolbar-title>
      </q-toolbar>

      <q-tabs align="center">
        <q-route-tab icon="home" to="/" label="Página inicial" />
        <q-route-tab icon="groups" to="/fornecedores" label="Fornecedores" />
        <q-route-tab icon="support_agent" to="/servicos" label="Serviços" />
        <q-route-tab icon="gavel" to="/auditoria" label="Auditoria" />
      </q-tabs>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer elevated class="bg-indigo-10 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <div>Desenvolvido pela Temperosoft</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>
