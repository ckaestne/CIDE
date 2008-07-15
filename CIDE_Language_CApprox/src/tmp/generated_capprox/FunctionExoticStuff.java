package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionExoticStuff extends GenASTNode {
  public FunctionExoticStuff(ASTStringNode literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("literal", literal)
    }, firstToken, lastToken);
  }
  public FunctionExoticStuff(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionExoticStuff(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLiteral() {
    return ((PropertyOne<ASTStringNode>)getProperty("literal")).getValue();
  }
}
