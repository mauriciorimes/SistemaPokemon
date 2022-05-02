package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.Pokemon;

public class PokemonDaoJpa implements InterfaceDao<Pokemon> {

    @Override
    public void incluir(Pokemon entidade) throws Exception {
          EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void editar(Pokemon entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Pokemon entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();

            Pokemon p1 = em.find(Pokemon.class, entidade.getId());
            em.remove(p1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Pokemon pesquisarPorId(int id) throws Exception {
        Pokemon p = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();

            p = em.find(Pokemon.class, id);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public List<Pokemon> listar() throws Exception {
        List<Pokemon> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Pokemon p").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;
    }
    
}
