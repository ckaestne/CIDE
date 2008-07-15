package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class complex_regular_expression_unit3 extends complex_regular_expression_unit {
  public complex_regular_expression_unit3(character_list character_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<character_list>("character_list", character_list)
    }, firstToken, lastToken);
  }
  public complex_regular_expression_unit3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new complex_regular_expression_unit3(cloneProperties(),firstToken,lastToken);
  }
  public character_list getCharacter_list() {
    return ((PropertyOne<character_list>)getProperty("character_list")).getValue();
  }
}
