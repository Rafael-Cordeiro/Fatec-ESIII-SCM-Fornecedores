<template>
  <q-page padding>
    <div class="q-ma-xl">
      <span>Listar</span>
      <q-toggle
        color="indigo-10"
        v-model="insertUpdateDialog"
        size="xl"
      />
      <span>Inserir</span>
    </div>
    <!-- LISTA DE FORNECEDORES -->
    <div class="row q-col-gutter-md">
      <div class="col-3" v-if="!insertUpdateDialog">
        <!-- Form Filtro -->
        <q-card class="bg-indigo-10 text-white">
          <q-card-section>
            <span class="big-text">Filtrar</span>
          </q-card-section>
          <q-card-section>
            <q-input
              v-model="filtroRazaoSocial"
              dark
              label="Razão Social"
              dense
              filled
            />
            <q-input
              v-model="filtroNomeFantasia"
              dark
              label="Nome Fantasia"
              dense
              filled
            />
            <q-input
              v-model="filtroCidade"
              dark
              label="Cidade"
              dense
              filled
            />
            <q-input
              v-model="filtroEstado"
              dark
              label="Estado"
              dense
              filled
            />
            <q-input
              v-model="filtroCnpj"
              dark
              label="CNPJ"
              dense
              filled
            />
            <q-input
              v-model="filtroCnae"
              dark
              label="CNAE"
              dense
              filled
            />
            <q-input
              v-model="filtroIe"
              dark
              label="Inscrição Estadual"
              dense
              filled
            />
            <q-input
              v-model="filtroIm"
              dark
              label="Inscrição Municipal"
              dense
              filled
            />
            <q-input
              v-model="filtroNomeContato"
              dark
              label="Nome de contato associado"
              dense
              filled
            />
          </q-card-section>
        </q-card>
      </div>
      <div class="col-9" v-if="!insertUpdateDialog">
        <!-- Modal de consulta -->
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
        <!-- Tabela de fornecedores -->
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
      <!-- Form Insert / Update -->
      <div class="col-12" v-else>
        <q-card class="card-insert-form">
          <q-form></q-form>
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
                      mask="##.###.###/####-##"
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
                      mask="#####-###"
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
                  <q-card-section v-for="(contato, index) in fornecedor.contatos" :key="contato.index">
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
                        mask="+##"
                      />
                      <q-input
                        outlined
                        class="col-md-1 q-pa-md"
                        label="DDD"
                        v-model="contato.telefone.ddd"
                        mask="###"
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
                      <q-btn flat rounded color="negative" label="Remover este contato" @click="removerContato(index)" icon="remove"/>
                    </q-card-section>
                    <q-separator/>
                  </q-card-section>
                  <q-card-actions class="row" align="center">
                    <q-btn flat rounded color="negative" label="Remover contato" @click="PopContato" icon="remove"/>
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
      </div>
    </div>

    <!-- FORMULÁRIO INSERT | UPDATE -->

  </q-page>
</template>

<script>
import { useQuasar } from 'quasar'
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ListaFornecedores',
  setup () {
    const $q = useQuasar()
    return {
      $q
    }
  },
  data () {
    return {
      // =============== dados de form filtro ===============
      filtroRazaoSocial: '',
      filtroNomeFantasia: '',
      filtroCidade: '',
      filtroEstado: '',
      filtroCnpj: '',
      filtroCnae: '',
      filtroIe: '',
      filtroIm: '',
      filtroNomeContato: '',
      // =============== dados de lista ===============
      contatoColumns: [
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true },
        { name: 'nome', label: 'Nome', field: 'nome', align: 'left', sortable: true },
        { name: 'email', label: 'Email', field: 'email', align: 'left', sortable: true },
        { name: 'departamento', label: 'Departamento', field: 'departamento', align: 'left', sortable: true },
        { name: 'telefone', label: 'Telefone', field: 'telefone', align: 'left', sortable: true },
        { name: 'tipoTelefone', label: 'Tipo de Telefone', field: 'tipoTelefone', align: 'left', sortable: true }
      ],
      contatosTable: [],
      columns: [
        { name: 'action', label: 'Ação', field: 'action', align: 'center', sortable: false },
        { name: 'tipoCadastro', label: 'Tipo Cadastro', field: 'tipoCadastro', align: 'left', sortable: true },
        { name: 'razaoSocial', label: 'Razão Social', field: 'razaoSocial', align: 'left', sortable: true },
        { name: 'nomeFantasia', label: 'Nome Fantasia', field: 'nomeFantasia', align: 'left', sortable: true },
        { name: 'cnpj', label: 'CNPJ', field: 'cnpj', align: 'left', sortable: true },
        { name: 'cidade', label: 'Cidade', field: 'cidade', align: 'left', sortable: true },
        { name: 'estado', label: 'Estado', field: 'estado', align: 'left', sortable: true },
        { name: 'pais', label: 'Pais', field: 'pais', align: 'left', sortable: true },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true }
      ],
      listaFornecedoresSource: [],
      listaFornecedores: [],
      insertUpdateDialog: false,
      viewDialog: false,
      maximizedToggle: false,
      // =============== dados de form ===============
      isUpdate: false,
      columnsProdutosTable: [
        { name: 'id', label: 'ID', field: 'id', align: 'left', sortable: true },
        { name: 'descricao', label: 'Descrição', field: 'descricao', align: 'left', sortable: true },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true },
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
            descricao: ' '
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
              descricao: ' '
            }
          },
          contatos: [],
          produtos: []
        }
      },
      Contato () {
        return {
          codigo: '',
          nome: '',
          email: '',
          departamento: '',
          telefone: {
            tipoTelefone: {
              descricao: ' '
            },
            ddd: '',
            ddi: '',
            numero: '',
            ramal: '',
            tipoCadastro: {
              descricao: ''
            }
          },
          tipoCadastro: {
            descricao: ''
          }
        }
      },
      Produto () {
        return {
          id: null,
          codigo: '',
          descricao: '',
          tipoCadastro: {
            descricao: ''
          }
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
    // Filters
    filtroRazaoSocial () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroNomeFantasia () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroCidade () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroEstado () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroCnpj () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroCnae () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroIe () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroIm () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    filtroNomeContato () {
      this.listaFornecedores = this.filterFornecedorList()
    },
    // Dialogs
    insertUpdateDialog () {
      if (!this.insertUpdateDialog) {
        this.fornecedor = this.Fornecedor()
        this.isUpdate = false
        this.comboProdutos.forEach(produto => {
          produto.usar = false
        })
        this.filtroRazaoSocial = ''
        this.filtroNomeFantasia = ''
        this.filtroCidade = ''
        this.filtroEstado = ''
        this.filtroCnpj = ''
        this.filtroCnae = ''
        this.filtroIe = ''
        this.filtroIm = ''
        this.filtroNomeContato = ''
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
    //  =============== métodos de filtro ===============
    filterFornecedorList () {
      return this.listaFornecedoresSource.filter(item =>
        item.razaoSocial.toLocaleLowerCase().includes(this.filtroRazaoSocial.toLocaleLowerCase()) &&
        item.nomeFantasia.toLocaleLowerCase().includes(this.filtroNomeFantasia.toLocaleLowerCase()) &&
        item.endereco.cidade.descricao.toLocaleLowerCase().includes(this.filtroCidade.toLocaleLowerCase()) &&
        item.endereco.cidade.estado.descricao.toLocaleLowerCase().includes(this.filtroEstado.toLocaleLowerCase()) &&
        item.documentos[0].codigo.toLocaleLowerCase().includes(this.filtroCnpj.toLocaleLowerCase()) &&
        item.documentos[1].codigo.toLocaleLowerCase().includes(this.filtroCnae.toLocaleLowerCase()) &&
        item.documentos[2].codigo.toLocaleLowerCase().includes(this.filtroIe.toLocaleLowerCase()) &&
        item.documentos[3].codigo.toLocaleLowerCase().includes(this.filtroIm.toLocaleLowerCase()) &&
        item.contatos.find(item => item.nome.toLocaleLowerCase().includes(this.filtroNomeContato))
      ).map(fornecedor => {
        return {
          fornecedor: {
            ...fornecedor
          },
          id: fornecedor.id,
          razaoSocial: fornecedor.razaoSocial,
          nomeFantasia: fornecedor.nomeFantasia,
          cnpj: fornecedor.documentos[0].codigo,
          codigo: fornecedor.codigo,
          cidade: fornecedor.endereco.cidade.descricao,
          estado: fornecedor.endereco.cidade.estado.descricao,
          pais: fornecedor.endereco.cidade.estado.pais.descricao,
          tipoCadastro: fornecedor.tipoCadastro.descricao
        }
      })
    },
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
          cnpj: fornecedor.documentos[0].codigo,
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
          this.listaFornecedoresSource = res.data
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
          codigo: contato.codigo,
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
          .then(res => {
            this.$q.notify({
              message: 'Atualização realizada com sucesso',
              color: 'positive',
              icon: 'check_circle_outline'
            })
            this.fetchFornecedores()
            this.insertUpdateDialog = false
          }).catch(error => {
            console.log(error.response.data)
            this.$q.notify({
              message: error.response.data,
              color: 'negative',
              icon: 'error_outline'
            })
          })
      } else {
        this.$axios.post('http://localhost:9999/api/fornecedor/', this.fornecedor)
          .then(res => {
            this.$q.notify({
              message: 'Cadastro realizado com sucesso',
              color: 'positive',
              icon: 'check_circle_outline'
            })
            this.fetchFornecedores()
            this.insertUpdateDialog = false
          }).catch(error => {
            console.log(error.response.data)
            this.$q.notify({
              message: error.response.data,
              color: 'negative',
              icon: 'error_outline'
            })
          })
      }
    },
    openInactivationDialog (fornecedor) {
      this.$axios.delete(`http://localhost:9999/api/fornecedor/${fornecedor.id}`)
        .then(res => {
          this.$q.notify({
            message: 'Fornecedor desativado com sucesso',
            color: 'positive',
            icon: 'check_circle_outline'
          })
          this.fetchFornecedores()
          this.insertUpdateDialog = false
        }).catch(error => {
          console.log(error.response.data)
          this.$q.notify({
            message: `Erro ao desativar fornecedor ${error.response.data}`,
            color: 'negative',
            icon: 'error_outline'
          })
        })
    },
    openActivationDialog (fornecedor) {
      this.$axios.post(`http://localhost:9999/api/fornecedor/${fornecedor.id}`)
        .then(res => {
          this.$q.notify({
            message: 'Fornecedor ativado com sucesso',
            color: 'positive',
            icon: 'check_circle_outline'
          })
          this.fetchFornecedores()
          this.insertUpdateDialog = false
        }).catch(error => {
          console.log(error.response.data)
          this.$q.notify({
            message: `Erro ao ativar fornecedor ${error.response.data}`,
            color: 'negative',
            icon: 'error_outline'
          })
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
    PopContato () {
      this.fornecedor.contatos.pop()
    },
    removerContato (index) {
      this.fornecedor.contatos.splice(index, 1)
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
      this.$axios.get('http://localhost:9999/api/produto/ativos')
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
