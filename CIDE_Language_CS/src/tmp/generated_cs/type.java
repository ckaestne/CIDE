package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type extends GenASTNode {
  public type(non_array_type non_array_type, rank_specifiers rank_specifiers, ASTTextNode text146, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<non_array_type>("non_array_type", non_array_type),
      new PropertyZeroOrOne<rank_specifiers>("rank_specifiers", rank_specifiers),
      new PropertyZeroOrOne<ASTTextNode>("text146", text146)
    }, firstToken, lastToken);
  }
  public type(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type(cloneProperties(),firstToken,lastToken);
  }
  public non_array_type getNon_array_type() {
    return ((PropertyOne<non_array_type>)getProperty("non_array_type")).getValue();
  }
  public rank_specifiers getRank_specifiers() {
    return ((PropertyZeroOrOne<rank_specifiers>)getProperty("rank_specifiers")).getValue();
  }
  public ASTTextNode getText146() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text146")).getValue();
  }
}
