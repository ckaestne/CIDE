package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LayoutHint extends GenASTNode {
  public LayoutHint(ASTTextNode text348, ASTTextNode text349, ASTTextNode text350, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text348", text348),
      new PropertyZeroOrOne<ASTTextNode>("text349", text349),
      new PropertyZeroOrOne<ASTTextNode>("text350", text350)
    }, firstToken, lastToken);
  }
  public LayoutHint(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LayoutHint(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText348() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text348")).getValue();
  }
  public ASTTextNode getText349() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text349")).getValue();
  }
  public ASTTextNode getText350() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text350")).getValue();
  }
}
