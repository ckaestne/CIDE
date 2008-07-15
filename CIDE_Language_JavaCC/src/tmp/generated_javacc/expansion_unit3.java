package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unit3 extends expansion_unit {
  public expansion_unit3(expansion_choices expansion_choices, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expansion_choices>("expansion_choices", expansion_choices)
    }, firstToken, lastToken);
  }
  public expansion_unit3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unit3(cloneProperties(),firstToken,lastToken);
  }
  public expansion_choices getExpansion_choices() {
    return ((PropertyOne<expansion_choices>)getProperty("expansion_choices")).getValue();
  }
}
