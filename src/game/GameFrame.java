package game;


import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import game.scene.MainMenuScene;
import game.scene.PlayScene;
import game.ui.GLayeredPane;
import game.ui.SceneManager;




public class GameFrame extends JFrame 
{


	private static final long serialVersionUID = 1L;
	GLayeredPane contentPane;
	Game game;
	
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public GameFrame() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 居中窗口
		{
			DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
			setBounds((mode.getWidth() - GameSetting.windowWidth)/2
					, (mode.getHeight() - GameSetting.windowHeight)/2
					, GameSetting.windowWidth, GameSetting.windowHeight);
		}
		contentPane = new GLayeredPane();
		contentPane.setSize(GameSetting.getWindowSize());
		contentPane.setPreferredSize(GameSetting.getWindowSize());
		setContentPane(contentPane);
		
		postInitialize();
		
		pack();
		
	}
	
	void postInitialize()
	{
		game = new Game();
		SceneManager sceneManager = game.getSceneManager();
		
		// 设置标题
		{
			String title = "%s v%s %s";
			title = String.format(title, GameSetting.title, GameSetting.Version,GameSetting.authorList.toString());
			setTitle(title);
		}
		
		
		
		//
		sceneManager.getStage().setLocation(0, 0);
		sceneManager.getStage().setSize(contentPane.getSize());
		contentPane.setLayer(sceneManager.getStage(), GLayeredPane.POPUP_LAYER);
		contentPane.add(sceneManager.getStage());
		contentPane.setBackground(Color.white);
		
		//
		sceneManager.putScene(new MainMenuScene());
		sceneManager.putScene(new PlayScene());
		//
		sceneManager.showScene("MainMenuScene");
//		System.out.println(GamePlayerAction.valueOf("FallDown") == GamePlayerAction.FallDown);
		//System.out.println("Comp test "+ (GamePlayerAction.Down == GamePlayerAction.FallDown));
		//sceneManager.showScene("PlayScene");
//		MessageBox.ShowNotify("有什么问题可以问我哦!");
//		MessageBox.ShowNotify("这就是个问题么?");
//		MessageBox.ShowNotify("我的网站 http://wener.me",0);
	}

}