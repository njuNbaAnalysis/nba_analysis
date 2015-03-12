package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;



public class SelectPanel extends JPanel {
	JButton submit;
	SelectJComboBox<String> location;
	SelectJComboBox<String>  union;
	SelectJComboBox<String>  depend;
	int width;
	int height;

	public SelectPanel(int width, int height) {
		this.setOpaque(false);
		this.setLayout(null);
		this.width = width;
		this.height = height;
		setLabel();
		setComboBox();
		setButton();
		
	}

	private void setLabel() {

	}

	private void setButton() {
		
		submit = new JButton();
		submit.setText("提交");
		submit.setBounds(width*5/8, height*11/20,width*1/4, height*7/20);
		submit.setForeground(null);
		submit.setContentAreaFilled(false);
		submit.setFocusPainted(false);
		submit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				SubmitActionPerformed(e);

			}

		});
		submit.addMouseListener(new MouseAdapter(){
	        public void mouseEntered(MouseEvent e){
	        	submit.setContentAreaFilled(true);
	        	submit.setBackground(new Color(148,148,148));
	        }
	        
	        public void mouseExited(MouseEvent e){
	        	submit.setContentAreaFilled(false);
	        }
	    });
		this.add(submit);
	}

	private void setComboBox() {
		location = new SelectJComboBox<String>();
		location.addItem("全部位置");
		location.addItem("中锋");
		location.addItem("后卫");
		location.addItem("前锋");
		location.setBounds(width/4, height/10, width/8, height*7/20);
		location.setBackground(new Color(69,69,69));
		location.setUI(new BasicComboBoxUI());
		location.setForeground(Color.white);
		//location.setBorder(BorderFactory.createEmptyBorder());
		this.add(location);
		
		union = new SelectJComboBox<String>();
		union.addItem("全部联盟");
		union.addItem("东部");
		union.addItem("西部");
		union.setBounds(width/2, height/10, width/8, height*7/20);
		union.setBackground(new Color(69,69,69));
		union.setUI(new BasicComboBoxUI());
		union.setForeground(Color.white);
		//union.setBorder(BorderFactory.createEmptyBorder());
		this.add(union);
		
		depend = new SelectJComboBox<String>();
		depend.addItem("得分");
		depend.addItem("篮板");
		depend.addItem("助攻");
		depend.addItem("得分/篮板/助攻");
		depend.addItem("盖帽");
		depend.addItem("抢断");
		depend.addItem("犯规");
		depend.addItem("失误");
		depend.addItem("分钟");
		depend.addItem("效率");
		depend.addItem("投篮");
		depend.addItem("三分");
		depend.addItem("罚球");
		depend.addItem("两双");
		depend.setBounds(width*3/4, height/10, width/8, height*7/20);
		union.setUI(new BasicComboBoxUI());
		depend.setForeground(Color.white);
		//depend.setBorder(BorderFactory.createEmptyBorder());
		this.add(depend);
	}

	private void SubmitActionPerformed(ActionEvent e) {
		//更新table
		this.validate();
		this.repaint();

	}
	
	private class SelectJComboBox<T> extends JComboBox<T>{
		SelectJComboBox(){
			this.setBackground(new Color(69,69,69));
			this.setUI(new BasicComboBoxUI());
			this.setForeground(Color.white);
			this.set
		}
	}
	
}
