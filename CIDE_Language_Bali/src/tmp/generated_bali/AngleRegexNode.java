package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AngleRegexNode extends Regex {
  public AngleRegexNode(AngleRegex angleRegex, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AngleRegex>("angleRegex", angleRegex)
    }, firstToken, lastToken);
  }
  public AngleRegexNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AngleRegexNode(cloneProperties(),firstToken,lastToken);
  }
  public AngleRegex getAngleRegex() {
    return ((PropertyOne<AngleRegex>)getProperty("angleRegex")).getValue();
  }
}
