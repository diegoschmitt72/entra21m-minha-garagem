package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Diego schmitt
 */
public class JFrameInicial extends JFrame implements JFrameComportamentosInterface {

    private JButton jButtonListaCarros, jButtonCadastroCarro, jButtonListaCategorias, jButtonListaAvioes;

    public JFrameInicial() {
        criarTela();
        criarComponentes();
        definirLocalizacao();
        adicionarComponenetes();
        adicionarOnClick();
    }

    @Override
    public void criarTela() {
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void criarComponentes() {
        jButtonListaCarros = new JButton("Lista de carros");
        jButtonCadastroCarro = new JButton("Cadastro de Carros");
        jButtonListaCategorias = new JButton("Lista de categorias");
        jButtonListaAvioes = new JButton("Lista de Avioes");
    }

    @Override
    public void definirLocalizacao() {
        jButtonListaCarros.setBounds(10, 10, 200, 35);
        jButtonCadastroCarro.setBounds(10, 55, 200, 35);
        jButtonListaCategorias.setBounds(10, 100, 200, 35);
        jButtonListaAvioes.setBounds(10, 145, 200, 35);
    }

    @Override
    public void adicionarComponenetes() {
        add(jButtonListaCarros);
        add(jButtonCadastroCarro);
        add(jButtonListaCategorias);
        add(jButtonListaAvioes);

    }

    @Override
    public void adicionarOnClick() {
        jButtonListaCarros.addActionListener((ActionEvent e) -> {
            JFrameListaCarros lista = new JFrameListaCarros();
        });

        jButtonCadastroCarro.addActionListener((ActionEvent e) -> {
            new JFrameCadastroCarro().setVisible(true);
        });

        jButtonListaCategorias.addActionListener((ActionEvent e) -> {
            new JFrameListaCategorias().setVisible(true);
        });
        
        jButtonListaAvioes.addActionListener((ActionEvent e) -> {
            new JFrameListaAvioes().setVisible(true);
        });
    }

}
