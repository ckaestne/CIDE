package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class regular_expr_production extends GenASTNode {
  public regular_expr_production(ArrayList<regexpr_spec> regexpr_spec, regular_expr_productionPrefix regular_expr_productionPrefix, regexpr_kind regexpr_kind, ASTTextNode text464, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<regexpr_spec>("regexpr_spec", regexpr_spec),
      new PropertyZeroOrOne<regular_expr_productionPrefix>("regular_expr_productionPrefix", regular_expr_productionPrefix),
      new PropertyOne<regexpr_kind>("regexpr_kind", regexpr_kind),
      new PropertyZeroOrOne<ASTTextNode>("text464", text464)
    }, firstToken, lastToken);
  }
  public regular_expr_production(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new regular_expr_production(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<regexpr_spec> getRegexpr_spec() {
    return ((PropertyList<regexpr_spec>)getProperty("regexpr_spec")).getValue();
  }
  public regular_expr_productionPrefix getRegular_expr_productionPrefix() {
    return ((PropertyZeroOrOne<regular_expr_productionPrefix>)getProperty("regular_expr_productionPrefix")).getValue();
  }
  public regexpr_kind getRegexpr_kind() {
    return ((PropertyOne<regexpr_kind>)getProperty("regexpr_kind")).getValue();
  }
  public ASTTextNode getText464() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text464")).getValue();
  }
}
