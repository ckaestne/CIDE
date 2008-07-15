package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CREUPostfix4 extends CREUPostfix {
  public CREUPostfix4(ASTStringNode integer_literal, CREUPostfixPP cREUPostfixPP, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("integer_literal", integer_literal),
      new PropertyZeroOrOne<CREUPostfixPP>("cREUPostfixPP", cREUPostfixPP)
    }, firstToken, lastToken);
  }
  public CREUPostfix4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CREUPostfix4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("integer_literal")).getValue();
  }
  public CREUPostfixPP getCREUPostfixPP() {
    return ((PropertyZeroOrOne<CREUPostfixPP>)getProperty("cREUPostfixPP")).getValue();
  }
}
