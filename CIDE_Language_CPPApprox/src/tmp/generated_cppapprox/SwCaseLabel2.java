package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwCaseLabel2 extends SwCaseLabel {
  public SwCaseLabel2(ASTStringNode other, ASTStringNode literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("other", other),
      new PropertyOne<ASTStringNode>("literal", literal)
    }, firstToken, lastToken);
  }
  public SwCaseLabel2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SwCaseLabel2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getOther() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("other")).getValue();
  }
  public ASTStringNode getLiteral() {
    return ((PropertyOne<ASTStringNode>)getProperty("literal")).getValue();
  }
}
