import React, { useState, useEffect } from "react";
import axios from "axios";
import { 
  Button, 
  TextField, 
  Card, 
  CardContent, 
  Typography, 
  MenuItem, 
  Table, 
  TableHead, 
  TableBody, 
  TableRow, 
  TableCell,
  Container,
  Grid,
  Box,
  CircularProgress,
  Alert,
  Snackbar,
  AppBar,
  Toolbar,
  Divider,
} from "@mui/material";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import MeetingRoomIcon from '@mui/icons-material/MeetingRoom';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import EventAvailableIcon from '@mui/icons-material/EventAvailable';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import BookmarkAddedIcon from '@mui/icons-material/BookmarkAdded';
import TableViewIcon from '@mui/icons-material/TableView';
import HomeIcon from '@mui/icons-material/Home';

const theme = createTheme({
  palette: {
    primary: {
      main: '#3f51b5',
    },
    secondary: {
      main: '#f50057',
    },
  },
});

const API_BASE_URL = {
  usuarios: "http://localhost/api/usuarios",
  salas: "http://localhost/api/salas",
  reservas: "http://localhost/api/reservas",
};

export default function App() {
  const [usuarios, setUsuarios] = useState([]);
  const [salas, setSalas] = useState([]);
  const [reservas, setReservas] = useState([]);
  const [form, setForm] = useState({ 
    nome: "", 
    email: "", 
    cpf: "", 
    dataNascimento: "",
    endereco: {
      cidade: "",
      estado: "",
      cep: "",
      rua: ""
    }
  });
  const [salaForm, setSalaForm] = useState({ nome: "", capacidade: 0 });
  const [reservaForm, setReservaForm] = useState({ usuarioId: "", salaId: "", dataHora: "" });
  const [cpf, setCpf] = useState("");
  const [loading, setLoading] = useState(false);
  const [notification, setNotification] = useState({ open: false, message: "", severity: "success" });
  
  // Carregar dados de salas, reservas e usuários na montagem do componente
  useEffect(() => {
    fetchData("salas", setSalas);
    fetchData("reservas", setReservas);
    fetchData("usuarios", setUsuarios);
    // eslint-disable-next-line
  }, []);

  const fetchData = async (endpoint, setter) => {
    setLoading(true);
    try {
      const res = await axios.get(API_BASE_URL[endpoint]);
      console.log(`Dados de ${endpoint} carregados com sucesso:`, res.data);
      setter(res.data);
    } catch (error) {
      console.error(`Erro ao buscar ${endpoint}:`, error);
      showNotification(`Erro ao buscar ${endpoint}`, "error");
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };
  
  const handleEnderecoChange = (e) => {
    setForm({
      ...form,
      endereco: {
        ...form.endereco,
        [e.target.name]: e.target.value
      }
    });
  };

  const showNotification = (message, severity = "success") => {
    setNotification({ open: true, message, severity });
  };

  const handleCloseNotification = () => {
    setNotification({ ...notification, open: false });
  };

  const clearForm = () => {
    setForm({ 
      nome: "", 
      email: "", 
      cpf: "", 
      dataNascimento: "",
      endereco: {
        cidade: "",
        estado: "",
        cep: "",
        rua: ""
      }
    });
  };

  const clearSalaForm = () => {
    setSalaForm({ nome: "", capacidade: 0 });
  };

  const clearReservaForm = () => {
    setReservaForm({ usuarioId: "", salaId: "", dataHora: "" });
  };

  const handleSubmitUsuario = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await axios.post(API_BASE_URL.usuarios, {
        nome: form.nome,
        email: form.email,
        cpf: form.cpf || null,
        dataNascimento: form.dataNascimento || null,
        endereco: {
          cidade: form.endereco.cidade || null,
          estado: form.endereco.estado || null,
          cep: form.endereco.cep || null,
          rua: form.endereco.rua || null
        }
      });
      showNotification("Usuário criado com sucesso!");
      fetchData("usuarios", setUsuarios);
      clearForm();
    } catch (error) {
      console.error("Erro ao criar usuário:", error);
      showNotification("Erro ao criar usuário", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleSubmitSala = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await axios.post(API_BASE_URL.salas, salaForm);
      showNotification("Sala criada com sucesso!");
      fetchData("salas", setSalas);
      clearSalaForm();
    } catch (error) {
      console.error("Erro ao criar sala:", error);
      showNotification("Erro ao criar sala", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleSubmitReserva = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await axios.post(API_BASE_URL.reservas, {
        usuarioId: reservaForm.usuarioId,
        salaId: reservaForm.salaId,
        dataHora: reservaForm.dataHora
      });
      showNotification("Reserva criada com sucesso!");
      fetchData("reservas", setReservas);
      clearReservaForm();
    } catch (error) {
      console.error("Erro ao criar reserva:", error);
      showNotification("Erro ao criar reserva", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleBuscarPorCpf = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await axios.get(`${API_BASE_URL.usuarios}/${cpf}`);
      if (response.data) {
        showNotification(`Usuário encontrado: ${response.data.nome}`, "success");
      } else {
        showNotification("Usuário não encontrado!", "warning");
      }
    } catch (error) {
      console.error("Erro ao buscar usuário por CPF:", error);
      showNotification("Erro ao buscar usuário", "error");
    } finally {
      setLoading(false);
      setCpf("");
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <div className="min-h-screen bg-gray-50">
        <AppBar position="static" className="mb-6">
          <Toolbar>
            <MeetingRoomIcon className="mr-2" />
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              Sistema de Gerenciamento de Salas e Reservas
            </Typography>
          </Toolbar>
        </AppBar>

        <Container maxWidth="lg" className="py-6">
          <Snackbar 
            open={notification.open} 
            autoHideDuration={6000} 
            onClose={handleCloseNotification}
            anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
          >
            <Alert onClose={handleCloseNotification} severity={notification.severity} sx={{ width: '100%' }}>
              {notification.message}
            </Alert>
          </Snackbar>

          <Grid container spacing={4}>
            {/* Coluna esquerda */}
            <Grid item xs={12} md={6}>
              {/* Formulário de Criar Usuário */}
              <Card elevation={3} className="mb-6 overflow-hidden">
                <Box className="bg-blue-600 text-white px-4 py-3 flex items-center">
                  <PersonAddIcon className="mr-2" />
                  <Typography variant="h6">Cadastrar Usuário</Typography>
                </Box>
                <CardContent className="p-4">
                  <form onSubmit={handleSubmitUsuario} className="space-y-4">
                    <Grid container spacing={2}>
                      <Grid item xs={12}>
                        <TextField 
                          fullWidth 
                          label="Nome" 
                          name="nome" 
                          value={form.nome} 
                          onChange={handleChange} 
                          variant="outlined"
                          required
                        />
                      </Grid>
                      <Grid item xs={12}>
                        <TextField 
                          fullWidth 
                          label="Email" 
                          name="email" 
                          type="email"
                          value={form.email} 
                          onChange={handleChange} 
                          variant="outlined"
                          required
                        />
                      </Grid>
                      <Grid item xs={6}>
                        <TextField 
                          fullWidth 
                          label="CPF" 
                          name="cpf" 
                          value={form.cpf} 
                          onChange={handleChange} 
                          variant="outlined"
                          inputProps={{ maxLength: 14 }}
                        />
                      </Grid>
                      <Grid item xs={6}>
                        <TextField 
                          fullWidth 
                          label="Data de Nascimento" 
                          name="dataNascimento" 
                          type="date" 
                          value={form.dataNascimento} 
                          onChange={handleChange} 
                          InputLabelProps={{ shrink: true }} 
                          variant="outlined"
                        />
                      </Grid>
                      
                      {/* Seção de Endereço */}
                      <Grid item xs={12} className="mt-3">
                        <Box className="flex items-center mb-2">
                          <HomeIcon className="mr-2 text-blue-600" />
                          <Typography variant="subtitle1" className="font-medium">
                            Informações de Endereço
                          </Typography>
                        </Box>
                        <Divider className="mb-3" />
                      </Grid>
                      
                      <Grid item xs={6}>
                        <TextField 
                          fullWidth 
                          label="Cidade" 
                          name="cidade" 
                          value={form.endereco.cidade} 
                          onChange={handleEnderecoChange} 
                          variant="outlined"
                        />
                      </Grid>
                      <Grid item xs={6}>
                        <TextField 
                          fullWidth 
                          label="Estado" 
                          name="estado" 
                          value={form.endereco.estado} 
                          onChange={handleEnderecoChange} 
                          variant="outlined"
                        />
                      </Grid>
                      <Grid item xs={6}>
                        <TextField 
                          fullWidth 
                          label="CEP" 
                          name="cep" 
                          value={form.endereco.cep} 
                          onChange={handleEnderecoChange} 
                          variant="outlined"
                          inputProps={{ maxLength: 9 }}
                        />
                      </Grid>
                      <Grid item xs={6}>
                        <TextField 
                          fullWidth 
                          label="Rua" 
                          name="rua" 
                          value={form.endereco.rua} 
                          onChange={handleEnderecoChange} 
                          variant="outlined"
                        />
                      </Grid>
                    </Grid>
                    <Box className="flex justify-end mt-4">
                      <Button 
                        variant="contained" 
                        color="primary" 
                        type="submit"
                        startIcon={<AddIcon />}
                        disabled={loading}
                        className="mt-4"
                      >
                        {loading ? <CircularProgress size={24} /> : "Cadastrar Usuário"}
                      </Button>
                    </Box>
                  </form>
                </CardContent>
              </Card>

              {/* Formulário de Criar Sala */}
              <Card elevation={3} className="mb-6 overflow-hidden">
                <Box className="bg-purple-600 text-white px-4 py-3 flex items-center">
                  <MeetingRoomIcon className="mr-2" />
                  <Typography variant="h6">Cadastrar Sala</Typography>
                </Box>
                <CardContent className="p-4">
                  <form onSubmit={handleSubmitSala} className="space-y-4">
                    <Grid container spacing={2}>
                      <Grid item xs={7}>
                        <TextField 
                          fullWidth 
                          label="Nome da Sala" 
                          name="nome" 
                          value={salaForm.nome} 
                          onChange={(e) => setSalaForm({ ...salaForm, nome: e.target.value })} 
                          variant="outlined"
                          required
                        />
                      </Grid>
                      <Grid item xs={5}>
                        <TextField 
                          fullWidth 
                          label="Capacidade" 
                          name="capacidade" 
                          type="number"
                          value={salaForm.capacidade} 
                          onChange={(e) => setSalaForm({ ...salaForm, capacidade: e.target.value })} 
                          variant="outlined"
                          required
                          InputProps={{ inputProps: { min: 1 } }}
                        />
                      </Grid>
                    </Grid>
                    <Box className="flex justify-end mt-4">
                      <Button 
                        variant="contained" 
                        color="secondary" 
                        type="submit"
                        startIcon={<AddIcon />}
                        disabled={loading}
                      >
                        {loading ? <CircularProgress size={24} /> : "Cadastrar Sala"}
                      </Button>
                    </Box>
                  </form>
                </CardContent>
              </Card>

              {/* Formulário de Buscar Usuário por CPF */}
              <Card elevation={3} className="overflow-hidden">
                <Box className="bg-green-600 text-white px-4 py-3 flex items-center">
                  <SearchIcon className="mr-2" />
                  <Typography variant="h6">Buscar Usuário por CPF</Typography>
                </Box>
                <CardContent className="p-4">
                  <form onSubmit={handleBuscarPorCpf} className="space-y-4">
                    <TextField 
                      fullWidth 
                      label="CPF" 
                      name="cpf" 
                      value={cpf} 
                      onChange={(e) => setCpf(e.target.value)} 
                      variant="outlined"
                      required
                    />
                    <Box className="flex justify-end mt-4">
                      <Button 
                        variant="contained" 
                        color="primary" 
                        type="submit"
                        startIcon={<SearchIcon />}
                        disabled={loading}
                      >
                        {loading ? <CircularProgress size={24} /> : "Buscar Usuário"}
                      </Button>
                    </Box>
                  </form>
                </CardContent>
              </Card>
            </Grid>

            {/* Coluna direita */}
            <Grid item xs={12} md={6}>
              {/* Formulário de Criar Reserva */}
              <Card elevation={3} className="mb-6 overflow-hidden">
                <Box className="bg-amber-600 text-white px-4 py-3 flex items-center">
                  <EventAvailableIcon className="mr-2" />
                  <Typography variant="h6">Criar Reserva</Typography>
                </Box>
                <CardContent className="p-4">
                  <form onSubmit={handleSubmitReserva} className="space-y-4">
                    <Grid container spacing={2}>
                      <Grid item xs={12}>
                        <TextField 
                          select 
                          fullWidth 
                          label="Usuário" 
                          name="usuarioId" 
                          value={reservaForm.usuarioId} 
                          onChange={(e) => setReservaForm({ ...reservaForm, usuarioId: e.target.value })}
                          variant="outlined"
                          required
                        >
                          {usuarios.length > 0 ? (
                            usuarios.map((user) => (
                              <MenuItem key={user.id} value={user.id}>{user.nome}</MenuItem>
                            ))
                          ) : (
                            <MenuItem disabled>Nenhum usuário cadastrado</MenuItem>
                          )}
                        </TextField>
                      </Grid>
                      <Grid item xs={12}>
                        <TextField 
                          select 
                          fullWidth 
                          label="Sala" 
                          name="salaId" 
                          value={reservaForm.salaId} 
                          onChange={(e) => setReservaForm({ ...reservaForm, salaId: e.target.value })}
                          variant="outlined"
                          required
                        >
                          {salas.length > 0 ? (
                            salas.map((sala) => (
                              <MenuItem key={sala.id} value={sala.id}>{sala.nome} (Cap: {sala.capacidade})</MenuItem>
                            ))
                          ) : (
                            <MenuItem disabled>Nenhuma sala cadastrada</MenuItem>
                          )}
                        </TextField>
                      </Grid>
                      <Grid item xs={12}>
                        <TextField 
                          fullWidth 
                          label="Data e Hora"
                          type="datetime-local" 
                          name="dataHora" 
                          value={reservaForm.dataHora} 
                          onChange={(e) => setReservaForm({ ...reservaForm, dataHora: e.target.value })}
                          InputLabelProps={{ shrink: true }}
                          variant="outlined"
                          required
                        />
                      </Grid>
                    </Grid>
                    <Box className="flex justify-end mt-4">
                      <Button 
                        variant="contained" 
                        color="primary" 
                        type="submit"
                        startIcon={<BookmarkAddedIcon />}
                        disabled={loading}
                      >
                        {loading ? <CircularProgress size={24} /> : "Confirmar Reserva"}
                      </Button>
                    </Box>
                  </form>
                </CardContent>
              </Card>

              {/* Tabela de Reservas */}
              <Card elevation={3} className="overflow-hidden">
                <Box className="bg-indigo-600 text-white px-4 py-3 flex items-center">
                  <TableViewIcon className="mr-2" />
                  <Typography variant="h6">Reservas Agendadas</Typography>
                </Box>
                <CardContent className="p-0">
                  {loading ? (
                    <Box className="flex justify-center items-center p-8">
                      <CircularProgress />
                    </Box>
                  ) : reservas.length > 0 ? (
                    <Table>
                      <TableHead className="bg-gray-100">
                        <TableRow>
                          <TableCell className="font-bold">Usuário</TableCell>
                          <TableCell className="font-bold">Sala</TableCell>
                          <TableCell className="font-bold">Data e Hora</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {reservas.map((reserva) => {
                          const usuario = usuarios.find(u => u.id === reserva.usuarioId);
                          const sala = salas.find(s => s.id === reserva.salaId);
                          
                          return (
                            <TableRow key={reserva.id} className="hover:bg-gray-50">
                              <TableCell>{usuario ? usuario.nome : reserva.usuarioId}</TableCell>
                              <TableCell>{sala ? sala.nome : reserva.salaId}</TableCell>
                              <TableCell>{new Date(reserva.dataHora).toLocaleString()}</TableCell>
                            </TableRow>
                          );
                        })}
                      </TableBody>
                    </Table>
                  ) : (
                    <Box className="p-6 text-center">
                      <Typography variant="body1" color="textSecondary">
                        Nenhuma reserva encontrada.
                      </Typography>
                    </Box>
                  )}
                </CardContent>
              </Card>
            </Grid>
          </Grid>
        </Container>
      </div>
    </ThemeProvider>
  );
}