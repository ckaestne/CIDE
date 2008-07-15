package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FieldInvoke extends GenASTNode {
  public FieldInvoke(InvokeTarget invokeTarget, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<InvokeTarget>("invokeTarget", invokeTarget),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public FieldInvoke(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FieldInvoke(cloneProperties(),firstToken,lastToken);
  }
  public InvokeTarget getInvokeTarget() {
    return ((PropertyOne<InvokeTarget>)getProperty("invokeTarget")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
