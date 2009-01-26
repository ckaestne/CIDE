package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class print_stmt3 extends print_stmt {
  public print_stmt3(ASTStringNode print2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("print2", print2)
    }, firstToken, lastToken);
  }
  public print_stmt3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new print_stmt3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPrint2() {
    return ((PropertyOne<ASTStringNode>)getProperty("print2")).getValue();
  }
}
