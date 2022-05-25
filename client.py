import grpc
import numpy as np
import torch
from PIL import Image
from torchvision import transforms

import image_classification_pb2
import image_classification_pb2_grpc

port = 50911

input_image = Image.open("dog.jpg")
preprocess = transforms.Compose([
    transforms.Resize(256),
    transforms.CenterCrop(224),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406],
                         std=[0.229, 0.224, 0.225]),
])
input_tensor = preprocess(input_image)
input_batch = input_tensor.unsqueeze(0)

request = image_classification_pb2.ClassifyRequest(
    shape=input_batch.shape,
    data=sum(sum(sum(input_batch.numpy().tolist(), []), []), []),
)

channel = grpc.insecure_channel(f'localhost:{port}')
stub = image_classification_pb2_grpc.ImageClassificationServiceStub(channel)

reply = stub.Classify(request)

result = np.array(reply.result)
probabilities = torch.nn.functional.softmax(torch.from_numpy(result), dim=0)

# Read the categories
with open("imagenet_classes.txt", "r") as f:
    categories = [s.strip() for s in f.readlines()]
# Show top categories per image
top5_prob, top5_catid = torch.topk(probabilities, 5)
for i in range(top5_prob.size(0)):
    print(categories[top5_catid[i]], top5_prob[i].item())
