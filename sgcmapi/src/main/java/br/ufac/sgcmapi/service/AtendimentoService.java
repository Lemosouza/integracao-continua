package br.ufac.sgcmapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@Service
public class AtendimentoService implements IService<Atendimento> {

    private AtendimentoRepository repo;
    private ProfissionalService servicoProfissional;

    public AtendimentoService(
            AtendimentoRepository repo,
            ProfissionalService servicoProfissional) {
        this.repo = repo;
        this.servicoProfissional = servicoProfissional;
    }

    @Override
    public List<Atendimento> get() {
        return repo.findAll();
    }

    @Override
    public Atendimento get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Atendimento save(Atendimento objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        Atendimento registro = repo.findById(id).orElse(null);
        registro.setStatus(EStatus.CANCELADO);
        repo.save(registro);
    }

    public Atendimento updateStatus(Long id) {
        Atendimento registro = repo.findById(id).orElse(null);
        registro.setStatus(registro.getStatus().proximo());
        repo.save(registro);
        return registro;
    }

    @Override
    public List<Atendimento> get(String termoBusca) {
        return repo.findByAll(termoBusca);
    }

    public List<String> getHorarios(Long profissional_id, LocalDate data) {
        Profissional profissional = servicoProfissional.get(profissional_id);
        List<Atendimento> atendimentos = repo.findByProfissionalAndDataAndStatusNot(profissional, data, EStatus.CANCELADO);
        List<String> horarios = new ArrayList<>();
        for (Atendimento item: atendimentos) {
            horarios.add(item.getHora().toString());
        }
        return horarios;
    }

}
