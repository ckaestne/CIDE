package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class print_stmt1 extends print_stmt {
  public print_stmt1(ASTStringNode print, ASTStringNode rshift, test test, print_stmtEndP print_stmtEndP, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("print", print),
      new PropertyOne<ASTStringNode>("rshift", rshift),
      new PropertyOne<test>("test", test),
      new PropertyZeroOrOne<print_stmtEndP>("print_stmtEndP", print_stmtEndP)
    }, firstToken, lastToken);
  }
  public print_stmt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new print_stmt1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPrint() {
    return ((PropertyOne<ASTStringNode>)getProperty("print")).getValue();
  }
  public ASTStringNode getRshift() {
    return ((PropertyOne<ASTStringNode>)getProperty("rshift")).getValue();
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public print_stmtEndP getPrint_stmtEndP() {
    return ((PropertyZeroOrOne<print_stmtEndP>)getProperty("print_stmtEndP")).getValue();
  }
}
