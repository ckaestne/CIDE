package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumBlock extends GenASTNode {
  public EnumBlock(ASTStringNode identifier, ASTStringNode findendccb, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<ASTStringNode>("findendccb", findendccb)
    }, firstToken, lastToken);
  }
  public EnumBlock(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumBlock(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ASTStringNode getFindendccb() {
    return ((PropertyOne<ASTStringNode>)getProperty("findendccb")).getValue();
  }
}
