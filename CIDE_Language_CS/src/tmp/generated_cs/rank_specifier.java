package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class rank_specifier extends GenASTNode {
  public rank_specifier(ArrayList<ASTTextNode> text147, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<ASTTextNode>("text147", text147)
    }, firstToken, lastToken);
  }
  public rank_specifier(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new rank_specifier(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTTextNode> getText147() {
    return ((PropertyZeroOrMore<ASTTextNode>)getProperty("text147")).getValue();
  }
}
