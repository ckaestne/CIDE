package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal extends GenASTNode {
  public Literal(ASTStringNode literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("literal", literal)
    }, firstToken, lastToken);
  }
  public Literal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLiteral() {
    return ((PropertyOne<ASTStringNode>)getProperty("literal")).getValue();
  }
}
