package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator11 extends AssignmentOperator {
  public AssignmentOperator11(ASTStringNode assor, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("assor", assor)
    }, firstToken, lastToken);
  }
  public AssignmentOperator11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator11(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAssor() {
    return ((PropertyOne<ASTStringNode>)getProperty("assor")).getValue();
  }
}
