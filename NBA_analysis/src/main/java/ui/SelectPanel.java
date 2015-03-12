package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;



public class SelectPanel extends JPanel {
	JButton submit;
	JComboBox<String> location;
	JComboBox<String>  union;
	JComboBox<String>  depend;
	int width;
	int height;

	public SelectPanel(int width, int height) {
		this.setOpaque(false);
		this.setLayout(new GridLayout(2,6));
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
		//submit.setBounds(width*4/5, height*2/3,width*1/6, height*1/4);
		submit.setForeground(null);
		submit.setContentAreaFilled(false);
		submit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				SubmitActionPerformed(e);

			}

		});
		submit.addMouseListener(new MouseAdapter(){
	        public void mouseEntered(MouseEvent e){
	        	submit.setContentAreaFilled(true);
	        	submit.setBackground(new Color(87, 89,91));
	        }
	        
	        public void mouseExited(MouseEvent e){
	        	submit.setContentAreaFilled(false);
	        }
	    });
		this.add(submit);
	}

	private void setComboBox() {
		location = new JComboBox<String>();
		location.addItem("全部位置");
		location.addItem("中锋");
		location.addItem("后卫");
		location.addItem("前锋");
		this.add(location);
		
		union = new JComboBox<String>();
		union.addItem("全部联盟");
		union.addItem("东部");
		union.addItem("西部");
		this.add(union);
		
		depend = new JComboBox<String>();
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
		this.add(depend);
	}

	private void SubmitActionPerformed(ActionEvent e) {
		//更新table
		this.validate();
		this.repaint();

	}
	
}
