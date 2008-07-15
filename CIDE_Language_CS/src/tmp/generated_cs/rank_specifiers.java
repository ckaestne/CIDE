package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class rank_specifiers extends GenASTNode {
  public rank_specifiers(ArrayList<rank_specifier> rank_specifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<rank_specifier>("rank_specifier", rank_specifier)
    }, firstToken, lastToken);
  }
  public rank_specifiers(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new rank_specifiers(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<rank_specifier> getRank_specifier() {
    return ((PropertyOneOrMore<rank_specifier>)getProperty("rank_specifier")).getValue();
  }
}
