package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class print_stmt2 extends print_stmt {
  public print_stmt2(ASTStringNode print1, print_stmtEndA print_stmtEndA, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("print1", print1),
      new PropertyOne<print_stmtEndA>("print_stmtEndA", print_stmtEndA)
    }, firstToken, lastToken);
  }
  public print_stmt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new print_stmt2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPrint1() {
    return ((PropertyOne<ASTStringNode>)getProperty("print1")).getValue();
  }
  public print_stmtEndA getPrint_stmtEndA() {
    return ((PropertyOne<print_stmtEndA>)getProperty("print_stmtEndA")).getValue();
  }
}
