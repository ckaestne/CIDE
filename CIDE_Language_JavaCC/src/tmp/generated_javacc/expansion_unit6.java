package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unit6 extends expansion_unit {
  public expansion_unit6(expansion_choices expansion_choices2, ExpModifier expModifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expansion_choices>("expansion_choices2", expansion_choices2),
      new PropertyZeroOrOne<ExpModifier>("expModifier", expModifier)
    }, firstToken, lastToken);
  }
  public expansion_unit6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unit6(cloneProperties(),firstToken,lastToken);
  }
  public expansion_choices getExpansion_choices2() {
    return ((PropertyOne<expansion_choices>)getProperty("expansion_choices2")).getValue();
  }
  public ExpModifier getExpModifier() {
    return ((PropertyZeroOrOne<ExpModifier>)getProperty("expModifier")).getValue();
  }
}
