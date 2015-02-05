package br.eti.kinoshita.myers;

import org.apache.commons.text.diff.CommandVisitor;
import org.apache.commons.text.diff.EditScript;
import org.apache.commons.text.diff.StringsComparator;

public class Myers01 {

    public static void main(String[] args) {
        StringsComparator sc = new StringsComparator("O Bruno eh um bom rapaz. Ele eh do Brasil.", "O Bruno foi um bom rapaz. Ele eh do Brasil .");
        EditScript<Character> es = sc.getScript();
        es.visit(new CommandVisitor<Character>() {
            
            boolean nlAdd = true;
            boolean nlRemove = true;

            @Override
            public void visitInsertCommand(Character object) {
                if (nlAdd) {
                    System.out.println();
                    System.out.print("+ ");
                    nlAdd = false;
                }
                System.out.print(object);
            }

            @Override
            public void visitKeepCommand(Character object) {
                if (!nlAdd) {
                    nlAdd = true;
                }
                if (!nlRemove) {
                    nlRemove = true;
                    System.out.println();
                }
                System.out.print(object);
            }

            @Override
            public void visitDeleteCommand(Character object) {
                if (nlRemove) {
                    System.out.println();
                    System.out.print("- ");
                    nlRemove = false;
                }
                System.out.print(object);
            }
        });
    }
    
}
