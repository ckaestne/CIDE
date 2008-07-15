package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class option_binding extends GenASTNode {
  public option_binding(OptionName optionName, OptionValue optionValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<OptionName>("optionName", optionName),
      new PropertyOne<OptionValue>("optionValue", optionValue)
    }, firstToken, lastToken);
  }
  public option_binding(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new option_binding(cloneProperties(),firstToken,lastToken);
  }
  public OptionName getOptionName() {
    return ((PropertyOne<OptionName>)getProperty("optionName")).getValue();
  }
  public OptionValue getOptionValue() {
    return ((PropertyOne<OptionValue>)getProperty("optionValue")).getValue();
  }
}
