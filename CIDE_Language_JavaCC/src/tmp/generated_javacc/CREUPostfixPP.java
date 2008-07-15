package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CREUPostfixPP extends GenASTNode {
  public CREUPostfixPP(ASTStringNode integer_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("integer_literal", integer_literal)
    }, firstToken, lastToken);
  }
  public CREUPostfixPP(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CREUPostfixPP(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger_literal() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("integer_literal")).getValue();
  }
}
