package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VarDeclToken2 extends VarDeclToken {
  public VarDeclToken2(ASTStringNode literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("literal", literal)
    }, firstToken, lastToken);
  }
  public VarDeclToken2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VarDeclToken2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLiteral() {
    return ((PropertyOne<ASTStringNode>)getProperty("literal")).getValue();
  }
}
