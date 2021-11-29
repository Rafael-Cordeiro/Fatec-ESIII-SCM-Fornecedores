<template>
  <q-page class="container q-pa-xl">
    <q-card class="card-insert-form">
      <q-form @submit="inserirFornecedor"></q-form>
      <!-- TABS - INSERÇÃO -->
      <q-form>
        <q-card-section class="items-center">
          <q-tabs v-model="tabFornecedores">
            <q-tab label="Dados primários" name="tab-dados-primarios" icon="person"/>
            <q-tab label="Endereço" name="tab-endereco" icon="location_on"/>
            <q-tab label="Contatos" name="tab-contatos" icon="email"/>
            <q-tab label="Produtos" name="tab-produtos" icon="local_shipping"/>
          </q-tabs>
          <q-separator/>
          <q-tab-panels v-model="tabFornecedores" animated keep-alive>
            <q-tab-panel name="tab-dados-primarios">
              <q-card-section class="row">
                <q-input
                  outlined
                  class="col-md-6 q-pa-md"
                  label="Razão Social"
                  v-model="fornecedor.razaoSocial"
                />
                <q-input
                  outlined
                  class="col-md-6 q-pa-md"
                  label="Nome Fantasia"
                  v-model="fornecedor.nomeFantasia"
                />
              </q-card-section>
              <q-card-section class="row">
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="CNPJ"
                  v-model="fornecedor.documentos[0].codigo"
                />
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="CNAE"
                  v-model="fornecedor.documentos[1].codigo"
                />
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="Inscrição Estadual"
                  v-model="fornecedor.documentos[2].codigo"
                />
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="Inscrição Municipal"
                  v-model="fornecedor.documentos[3].codigo"
                />
              </q-card-section>
            </q-tab-panel>
            <q-tab-panel name="tab-endereco">
              <q-card-section class="row">
                <q-select
                  outlined
                  class="col-md-3 q-pa-md"
                  v-model="fornecedor.endereco.cidade.estado.pais"
                  :options="comboPaises"
                  option-label="descricao"
                  label="País"
                  @update:model-value="createEstadoComboBox"
                />
                <q-select
                  outlined
                  class="col-md-3 q-pa-md"
                  v-model="fornecedor.endereco.cidade.estado"
                  :options="comboEstados"
                  option-label="descricao"
                  label="Estado"
                  @update:model-value="createCidadeComboBox"
                  :disable="disallowComboEstadosField"
                />
                <q-select
                  outlined
                  class="col-md-3 q-pa-md"
                  v-model="fornecedor.endereco.cidade"
                  :options="comboCidades"
                  option-label="descricao"
                  label="Cidade"
                  @update:model-value="allowEnderecoFields"
                  :disable="disallowComboCidadesField"
                />
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="CEP"
                  v-model="fornecedor.endereco.cep"
                  :disable="disallowEnderecoFields"
                />
              </q-card-section>
              <q-card-section class="row">
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="Logradouro"
                  v-model="fornecedor.endereco.logradouro"
                  :disable="disallowEnderecoFields"
                />
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="Número"
                  v-model="fornecedor.endereco.numero"
                  :disable="disallowEnderecoFields"
                />
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="Complemento"
                  v-model="fornecedor.endereco.complemento"
                  :disable="disallowEnderecoFields"
                />
                <q-input
                  outlined
                  class="col-md-3 q-pa-md"
                  label="Bairro"
                  v-model="fornecedor.endereco.bairro"
                  :disable="disallowEnderecoFields"
                />
              </q-card-section>
              <q-card-section class="row">
                <q-select
                  outlined
                  class="col-md-6 q-pa-md"
                  v-model="fornecedor.endereco.tipoEndereco.descricao"
                  :options="comboTipoEndereco"
                  option-label="descricao"
                  label="Tipo de Endereco"
                  :disable="disallowEnderecoFields"
                />
                <q-select
                  outlined
                  class="col-md-6 q-pa-md"
                  v-model="fornecedor.endereco.tipoLogradouro.descricao"
                  :options="comboTipoLogradouro"
                  option-label="descricao"
                  label="Tipo de Logradouro"
                  :disable="disallowEnderecoFields"
                />
              </q-card-section>
            </q-tab-panel>
            <q-tab-panel name="tab-contatos">
              <q-card-section v-for="contato in fornecedor.contatos" :key="contato.index">
                <q-card-section class="row">
                  <q-input
                    outlined
                    class="col-md-4 q-pa-md"
                    label="Nome"
                    v-model="contato.nome"
                  />
                  <q-input
                    outlined
                    class="col-md-4 q-pa-md"
                    label="Email"
                    v-model="contato.email"
                  />
                  <q-input
                    outlined
                    class="col-md-4 q-pa-md"
                    label="Departamento"
                    v-model="contato.departamento"
                  />
                </q-card-section>
                <q-card-section class="row">
                  <q-input
                    outlined
                    class="col-md-1 q-pa-md"
                    label="DDI"
                    v-model="contato.telefone.ddi"
                  />
                  <q-input
                    outlined
                    class="col-md-1 q-pa-md"
                    label="DDD"
                    v-model="contato.telefone.ddd"
                  />
                  <q-input
                    outlined
                    class="col-md-6 q-pa-md"
                    label="Telefone"
                    v-model="contato.telefone.numero"
                  />
                  <q-input
                    outlined
                    class="col-md-2 q-pa-md"
                    label="Ramal"
                    v-model="contato.telefone.ramal"
                  />
                  <q-select
                    outlined
                    class="col-md-2 q-pa-md"
                    label="Tipo de Telefone"
                    v-model="contato.telefone.tipoTelefone.descricao"
                    :options="comboTipoTelefone"
                    option-label="descricao"
                  />
                </q-card-section>
                <q-separator/>
              </q-card-section>
              <q-card-actions class="row" align="center">
                <q-btn flat rounded color="negative" label="Remover contato" @click="removerContato" icon="remove"/>
                <q-btn flat rounded color="primary" label="Adicionar contato" @click="adicionarContato" icon="add"/>
              </q-card-actions>
            </q-tab-panel>
            <q-tab-panel name="tab-produtos">
                <q-table
                  class="col my-sticky-header-table"
                  :rows="comboProdutos"
                  :columns="columns"
                >
                  <template v-slot:body-cell-action="props">
                    <q-td :props="props">
                      <q-toggle
                        color="primary"
                        size="sm"
                        dense
                        v-model="props.row.usar"
                        @click="incluirProduto(props.row)"
                      />
                    </q-td>
                  </template>
                </q-table>
            </q-tab-panel>
          </q-tab-panels>
        </q-card-section>
        <q-card-actions align="center" class="q-pa-md" id="insert-action-btn">
          <q-btn
            label="Inserir"
            color="primary"
            v-close-popup
            class="q-px-xl"
            type="submit"
            @click="onSubmit"
          />
        </q-card-actions>
      </q-form>
    </q-card>
    <code>{{ fornecedor }}</code><br/>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'FormFornecedor',
  data () {
    return {
      columns: [
        { name: 'id', label: 'ID', field: 'id', align: 'left', sortable: true },
        { name: 'descricao', label: 'Descrição', field: 'descricao', align: 'left', sortable: true },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true },
        // { name: 'usar', label: 'Em uso', field: 'usar', align: 'left', sortable: true },
        { name: 'action', label: 'Ação', field: 'action', align: 'right', sortable: false }
      ],
      fornecedor: {
        razaoSocial: '',
        nomeFantasia: '',
        documentos: [
          {
            codigo: '',
            tipoDocumento: {
              descricao: 'cnpj'
            }
          },
          {
            codigo: '',
            tipoDocumento: {
              descricao: 'cnae'
            }
          },
          {
            codigo: '',
            tipoDocumento: {
              descricao: 'inscricao-estadual'
            }
          },
          {
            codigo: '',
            tipoDocumento: {
              descricao: 'inscricao-municipal'
            }
          }
        ],
        endereco: {
          cep: '',
          logradouro: '',
          numero: '',
          complemento: '',
          bairro: '',
          cidade: {
            descricao: '',
            estado: {
              descricao: '',
              pais: {
                descricao: ''
              }
            }
          },
          tipoEndereco: {
            descricao: ''
          },
          tipoLogradouro: {
            descricao: ''
          }
        },
        contatos: [],
        produtos: []
      },
      Contato () {
        return {
          nome: '',
          email: '',
          departamento: '',
          telefone: {
            tipoTelefone: {
              descricao: ''
            },
            ddd: '',
            ddi: '',
            numero: '',
            ramal: ''
          }
        }
      },
      ProdutoHolder () {
        return {
          produto: {
            id: null,
            tipoCadastro: {
              descricao: ''
            },
            codigo: '',
            descricao: ''
          }
        }
      },
      Produto () {
        return {
          id: null,
          tipoCadastro: {
            descricao: ''
          },
          codigo: '',
          descricao: ''
        }
      },
      produtoHolderList: [],
      comboPaises: [],
      comboEstados: [],
      comboCidades: [],
      comboProdutos: [],
      produtoHolder: {
        produto: {}
      },
      comboTipoTelefone: ['Corporativo', 'Pessoal'],
      comboTipoEndereco: ['GALPAO', 'SALA_COMERCIAL', 'EDIFICIO'],
      comboTipoLogradouro: ['Avenida', 'Rua', 'Viela', 'Estrada', 'Rodovia'],
      disallowComboEstadosField: true,
      disallowComboCidadesField: true,
      disallowEnderecoFields: true,
      tabFornecedores: 'tab-dados-primarios'
    }
  },
  mounted () {
    this.createComboBoxes()
    this.fornecedor.contatos.push(this.Contato())
  },
  methods: {
    onSubmit () {
      this.$axios.post('http://localhost:9999/api/fornecedor/', this.fornecedor)
      this.$q.notify({
        message: 'Cadastro realizado com sucesso',
        color: 'positive',
        icon: 'check_circle_outline'
      })
    },
    incluirProduto (produto) {
      if (produto.usar) {
        this.fornecedor.produtos.push(produto)
        this.fornecedor.produtos.sort((a, b) => a.id - b.id)
      } else {
        const indice = this.fornecedor.produtos.indexOf(produto)
        this.fornecedor.produtos.splice(indice, 1)
      }
    },
    adicionarContato () {
      this.fornecedor.contatos.push(this.Contato())
    },
    removerContato () {
      this.fornecedor.contatos.pop()
    },
    inserirFornecedor () {
      console.log('insere')
    },
    createComboBoxes () {
      this.createPaisComboBox()
      this.createProdutosComboBox()
    },
    createPaisComboBox () {
      this.fetchPaises()
    },
    createEstadoComboBox (val) {
      this.fetchEstadosByIdPais(val.id)
      this.disallowComboEstadosField = false
    },
    createCidadeComboBox (val) {
      this.fetchCidadesByEstado(val.id)
      this.disallowComboCidadesField = false
    },
    createProdutosComboBox () {
      this.fetchProdutos()
    },
    allowEnderecoFields () {
      this.disallowEnderecoFields = false
    },
    fetchPaises () {
      this.$axios.get('http://localhost:9999/api/pais/')
        .then((res) => {
          this.comboPaises = res.data
        })
    },
    fetchEstadosByIdPais (paiId) {
      this.$axios.get('http://localhost:9999/api/estado/find-estados-by-pais-id', { params: { id: paiId } })
        .then((res) => {
          this.comboEstados = res.data
        })
    },
    fetchCidadesByEstado (estId) {
      this.$axios.get('http://localhost:9999/api/cidade/find-cidades-by-estado-id', { params: { id: estId } })
        .then((res) => {
          this.comboCidades = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    fetchProdutos () {
      this.$axios.get('http://localhost:9999/api/produto/')
        .then((res) => {
          this.comboProdutos = res.data.map(res => {
            return {
              ...res,
              usar: false
            }
          })
        })
    }
  }
})
</script>

<style></style>
