<template>
  <q-page class="container q-pa-xl">
    <span>Listar</span>
    <q-toggle
      color="indigo-10"
      v-model="insertUpdateDialog"
      size="xl"
    />
    <span>Inserir</span>
    <!-- LISTA DE FORNECEDORES -->
    <div v-if="!insertUpdateDialog">
      <q-dialog
        v-model="viewDialog"
        :maximized="maximizedToggle"
        transition-show="slide-up"
        transition-hide="slide-down"
      >
        <q-card class="bg-indigo-12 text-white">
          <q-bar>
            <q-space />

            <q-btn dense flat icon="minimize" @click="maximizedToggle = false" :disable="!maximizedToggle">
              <q-tooltip v-if="maximizedToggle" class="bg-white text-primary">Minimize</q-tooltip>
            </q-btn>
            <q-btn dense flat icon="crop_square" @click="maximizedToggle = true" :disable="maximizedToggle">
              <q-tooltip v-if="!maximizedToggle" class="bg-white text-primary">Maximize</q-tooltip>
            </q-btn>
            <q-btn dense flat icon="close" v-close-popup>
              <q-tooltip class="bg-white text-primary">Close</q-tooltip>
            </q-btn>
          </q-bar>

          <q-card-section>
            <div class="text-h6">Visualização</div>
          </q-card-section>

          <q-card-section class="q-pt-none">
            <div class="q-pa-xl">
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Razão Social</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.razaoSocial }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Nome Fantasia</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.nomeFantasia }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Código</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.codigo }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">CNPJ</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.documentos[0].codigo }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">CNAE</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.documentos[1].codigo }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Inscrição Estadual</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.documentos[2].codigo }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Inscrição Municipal</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.documentos[3].codigo }}</span></div>
              </div>
              <h3>Endereço</h3>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Logradouro</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.endereco.logradouro }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Tipo de logradouro</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.endereco.tipoLogradouro.descricao }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Número</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.endereco.numero }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Complemento</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ (fornecedor.endereco.complemento) ? fornecedor.endereco.complemento : 'N/A' }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Cidade</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.endereco.cidade.descricao }} - {{ fornecedor.endereco.cidade.estado.codigo }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">País</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.endereco.cidade.estado.pais.descricao }}</span></div>
              </div>
              <div class="row">
                <div class="col-sm-6"><span class="mid-text text-weight-bold">Tipo de endereço</span></div>
                <div class="col-sm-6" align="right"><span class="mid-text">{{ fornecedor.endereco.tipoEndereco.descricao }}</span></div>
              </div>
              <h3>Contatos</h3>
              <q-table
                class="col"
                :rows="contatosTable"
                :columns="contatoColumns"
                dense
              />
              <h3>Produtos fornecidos</h3>
              <q-table
                class="col"
                :rows="fornecedor.produtos"
                :columns="columnsProdutosTable"
                dense
              />
            </div>
          </q-card-section>
        </q-card>
      </q-dialog>
      <q-table
        :columns="columns"
        :rows="listaFornecedores"
        class="col my-sticky-header-table"
        row-key="id"
      >
        <template v-slot:body-cell-action="props">
          <q-td :props="props">
            <q-btn
              icon="create"
              color="indigo-10"
              size="sm"
              dense
              v-model="props.row.fornecedor"
              @click="openUpdateDialog(props.row.fornecedor)"
            />
            <q-btn
              icon="search"
              color="indigo-10"
              size="sm"
              dense
              class="q-ml-sm"
              @click="openViewDialog(props.row.fornecedor)"
            />
            <q-btn
              icon="delete"
              color="negative"
              size="sm"
              dense
              class="q-ml-sm"
              @click="openInactivationDialog(props.row.fornecedor)"
            />
            <q-btn
              icon="restore_from_trash"
              color="positive"
              size="sm"
              dense
              class="q-ml-sm"
              @click="openActivationDialog(props.row.fornecedor)"
            />
          </q-td>
        </template>
      </q-table>
    </div>
    <!-- FORMULÁRIO INSERT | UPDATE -->
    <div v-else>
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
                  <q-btn flat rounded color="indigo-10" label="Adicionar contato" @click="adicionarContato" icon="add"/>
                </q-card-actions>
              </q-tab-panel>
              <q-tab-panel name="tab-produtos">
                  <q-table
                    class="col my-sticky-header-table"
                    :rows="comboProdutos"
                    :columns="columnsProdutosTable"
                  >
                    <template v-slot:body-cell-action="props">
                      <q-td :props="props">
                        <q-toggle
                          color="indigo-10"
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
              v-if="!isUpdate"
              label="Inserir"
              color="indigo-10"
              class="q-px-xl"
              @click="onSubmit"
            />
            <q-btn
              v-else
              label="Atualizar"
              class="q-px-xl"
              color="indigo-10"
              @click="onSubmit"
            />
          </q-card-actions>
        </q-form>
      </q-card>
      <code>{{ fornecedor }}</code><br/>
    </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ListaFornecedores',
  setup () {
    return {}
  },
  data () {
    return {
      // =============== dados de lista ===============
      contatoColumns: [
        { name: 'nome', label: 'Nome', field: 'nome', align: 'left', sortable: true },
        { name: 'email', label: 'Email', field: 'email', align: 'left', sortable: true },
        { name: 'departamento', label: 'Departamento', field: 'departamento', align: 'left', sortable: true },
        { name: 'telefone', label: 'Telefone', field: 'telefone', align: 'left', sortable: true },
        { name: 'tipoTelefone', label: 'Tipo de Telefone', field: 'tipoTelefone', align: 'left', sortable: true }
      ],
      contatosTable: [],
      columns: [
        { name: 'action', label: 'Ação', field: 'action', align: 'center', sortable: false },
        // { name: 'id', label: 'ID', field: 'id', align: 'left', sortable: true },
        { name: 'tipoCadastro', label: 'Tipo Cadastro', field: 'tipoCadastro', align: 'left', sortable: true },
        { name: 'razaoSocial', label: 'Razão Social', field: 'razaoSocial', align: 'left', sortable: true },
        { name: 'nomeFantasia', label: 'Nome Fantasia', field: 'nomeFantasia', align: 'left', sortable: true },
        { name: 'cidade', label: 'Cidade', field: 'cidade', align: 'left', sortable: true },
        { name: 'estado', label: 'Estado', field: 'estado', align: 'left', sortable: true },
        { name: 'pais', label: 'Pais', field: 'pais', align: 'left', sortable: true },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true }
      ],
      listaFornecedores: [],
      insertUpdateDialog: false,
      viewDialog: false,
      maximizedToggle: true,
      // =============== dados de form ===============
      isUpdate: false,
      columnsProdutosTable: [
        { name: 'id', label: 'ID', field: 'id', align: 'left', sortable: true },
        { name: 'descricao', label: 'Descrição', field: 'descricao', align: 'left', sortable: true },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true },
        // { name: 'usar', label: 'Em uso', field: 'usar', align: 'left', sortable: true },
        { name: 'action', label: 'Ação', field: 'action', align: 'right', sortable: false }
      ],
      fornecedor: {
        razaoSocial: '',
        nomeFantasia: '',
        codigo: '',
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
            codigo: '',
            descricao: '',
            estado: {
              codigo: '',
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
      Fornecedor () {
        return {
          razaoSocial: '',
          nomeFantasia: '',
          codigo: '',
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
        }
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
      comboPaises: [],
      comboEstados: [],
      comboCidades: [],
      comboProdutos: [],
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
    // =============== mounted da lista ===============
    this.fetchFornecedores()
    // =============== mounted do form ===============
    this.createComboBoxes()
    this.fornecedor.contatos.push(this.Contato())
  },
  watch: {
    insertUpdateDialog () {
      if (!this.insertUpdateDialog) {
        this.fornecedor = this.Fornecedor()
        this.isUpdate = false
        this.comboProdutos.forEach(produto => {
          produto.usar = false
        })
      }
    },
    isUpdate () {
      if (this.isUpdate) {
        this.disallowComboEstadosField = false
        this.disallowComboCidadesField = false
        this.disallowEnderecoFields = false
      }
    },
    viewDialog () {
      if (!this.viewDialog) {
        this.fornecedor = this.Fornecedor()
        this.contatosTable = {}
      }
    }
  },
  methods: {
    // =============== métodos da lista ===============
    mountFornecedorTableData (fornecedores) {
      this.listaFornecedores = fornecedores.map((fornecedor) => {
        return {
          fornecedor: {
            ...fornecedor
          },
          id: fornecedor.id,
          razaoSocial: fornecedor.razaoSocial,
          nomeFantasia: fornecedor.nomeFantasia,
          codigo: fornecedor.codigo,
          cidade: fornecedor.endereco.cidade.descricao,
          estado: fornecedor.endereco.cidade.estado.descricao,
          pais: fornecedor.endereco.cidade.estado.pais.descricao,
          tipoCadastro: fornecedor.tipoCadastro.descricao
        }
      })
      console.log(this.listaFornecedores)
    },
    fetchFornecedores () {
      this.$axios.get('http://localhost:9999/api/fornecedor/')
        .then((res) => {
          this.listaFornecedores = res.data
          this.mountFornecedorTableData(this.listaFornecedores)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    openUpdateDialog (fornecedor) {
      if (fornecedor.tipoCadastro.descricao !== 'INATIVO') {
        this.isUpdate = true
        this.insertUpdateDialog = true
        console.log(fornecedor)
        fornecedor.produtos = fornecedor.produtos.map(produto => {
          return {
            ...produto,
            usar: true
          }
        })
        fornecedor.produtos.forEach(produto => {
          this.comboProdutos.find(item => item.id === produto.id).usar = true
        })
        this.fornecedor = fornecedor
      }
    },
    openViewDialog (fornecedor) {
      this.viewDialog = true
      this.fornecedor = fornecedor
      this.contatosTable = fornecedor.contatos.map(contato => {
        return {
          ...contato,
          nome: contato.nome,
          email: contato.email,
          departamento: contato.departamento,
          telefone: `${contato.telefone.ddi} ${contato.telefone.ddd} ${contato.telefone.numero} ${contato.telefone.ramal}`,
          tipoTelefone: contato.telefone.tipoTelefone.descricao
        }
      })
    },
    // =============== métodos do form ===============
    onSubmit () {
      if (this.isUpdate) {
        this.$axios.put(`http://localhost:9999/api/fornecedor/${this.fornecedor.id}`, this.fornecedor)
      } else {
        this.$axios.post('http://localhost:9999/api/fornecedor/')
      }
      document.location.reload(true)
    },
    openInactivationDialog (fornecedor) {
      this.$axios.delete(`http://localhost:9999/api/fornecedor/${fornecedor.id}`)
      document.location.reload(true)
    },
    openActivationDialog (fornecedor) {
      this.$axios.post(`http://localhost:9999/api/fornecedor/${fornecedor.id}`)
      document.location.reload(true)
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

<style lang="sass">
.my-sticky-header-table
  /* height or max-height is important */
  height: 500px

  .q-table__top,
  .q-table__bottom,
  thead tr:first-child th
    /* bg color is important for th; just specify one */
    background-color: #c5cae9

  thead tr th
    position: sticky
    z-index: 1
  thead tr:first-child th
    top: 0

  /* this is when the loading indicator appears */
  &.q-table--loading thead tr:last-child th
    /* height of all previous header rows */
    top: 48px
.big-text
  font-size: 24px
.mid-text
  font-size: 18px
</style>
