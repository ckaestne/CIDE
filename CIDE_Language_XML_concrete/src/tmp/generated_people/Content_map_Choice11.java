package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_map_Choice11 extends Content_map_Choice1 {
  public Content_map_Choice11(ArrayList<Content_map_Choice2> content_map_Choice2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Content_map_Choice2>("content_map_Choice2", content_map_Choice2)
    }, firstToken, lastToken);
  }
  public Content_map_Choice11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_map_Choice11(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Content_map_Choice2> getContent_map_Choice2() {
    return ((PropertyOneOrMore<Content_map_Choice2>)getProperty("content_map_Choice2")).getValue();
  }
}
